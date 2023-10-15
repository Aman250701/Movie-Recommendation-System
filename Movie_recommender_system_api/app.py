import pickle

import pandas as pd
from flask import Flask, jsonify, request
import requests

app = Flask(__name__)
# api_key = 8265bd1679663a7ea12ac168da84d2e8
movies_dict = pickle.load(open('movie_dict.pkl', 'rb'))
movies = pd.DataFrame(movies_dict)
similarity = pickle.load(open('similarity.pkl', 'rb'))


def fetchPosterLink(movie_id):
    response = requests.get(
        'https://api.themoviedb.org/3/movie/{}?api_key=8265bd1679663a7ea12ac168da84d2e8&language=en-US'.format(
            movie_id))
    data = response.json()
    print(data)
    full_path = "https://image.tmdb.org/t/p/w500/" + data['poster_path']
    return full_path


def recommend(movie):
    movie_index = movies[movies['title'] == movie].index[0]
    distances = similarity[movie_index]
    movies_list = sorted(list(enumerate(distances)), reverse=True, key=lambda x: x[1])[1:6]
    recommended_movies = []
    recommended_movies_posters = []
    for i in movies_list:
        movie_id = movies.iloc[i[0]].movie_id
        recommended_movies.append(movies.iloc[i[0]].title)
        # fetch poster from api
        recommended_movies_posters.append(fetchPosterLink(movie_id))
    return recommended_movies, recommended_movies_posters


@app.route('/', methods=['GET'])
def sendMoviesList():
    return jsonify(movies['title'].values.tolist())


@app.route('/recommend', methods=['POST'])
def predict():
    movie_name = request.form.get('movie_name')
    print("HELLO" + movie_name)
    recommendations, posters = recommend(movie_name)
    return jsonify(recommendations, posters)


if __name__ == '__main__':
    app.run(debug=True)
