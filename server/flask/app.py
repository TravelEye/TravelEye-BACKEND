from flask import Flask, jsonify, request
from flask_cors import CORS
import os
import numpy as np
import pandas as pd
import joblib
from sklearn.metrics.pairwise import cosine_similarity
from mysql_connector import MySqlConnector

app = Flask(__name__)
CORS(app)

# MySQL 계정정보 등록
app.config['MYSQL_HOST'] = os.environ.get('MYSQL_HOST')
app.config['MYSQL_USER'] = os.environ.get('MYSQL_USER')
app.config['MYSQL_PASSWORD'] = os.environ.get('MYSQL_PASSWORD')
app.config['MYSQL_DB'] = os.environ.get('MYSQL_DB')
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'


connector = MySqlConnector()
original_df, kmeans,scaler,scaled_df = connector.load_model_data()


@app.route('/predict', methods=['POST'])
def predict_cluster():
    # 새로운 사용자 데이터 받기
    data = request.get_json()
    new_user_data = pd.DataFrame([data])

    # 사용자 데이터 스케일링
    scaled_columns = ['GENDER', 'AGE_GRP', 'INCOME'] + [col for col in new_user_data.columns if col.startswith('TRAVEL_STYL_')]
    new_user_data_scaled = scaler.transform(new_user_data[scaled_columns])

    # 클러스터 예측
    cluster = kmeans.predict(new_user_data_scaled)

    # 코사인 유사도를 통한 사용자 추천
    df_scaled = scaler.transform(scaled_df[scaled_columns])  # 스케일링된 데이터를 사용
    similarity = cosine_similarity(new_user_data_scaled, df_scaled)
    similar_users = np.argsort(-similarity[0])[:10]  # 가장 유사한 10명 선택
    recommended_users = original_df.iloc[similar_users]['TRAVELER_ID'].tolist()

    return jsonify({'cluster': int(cluster[0]), 'recommended_users': recommended_users})

@app.route('/')
def home():
    return "Welcome to the Traveler Recommendation API!"


@app.route('/recommend', methods=['POST'])
def get_recommendations():
    data = request.get_json()
    if not data or 'latitude' not in data or 'longitude' not in data:
        return jsonify({'error': 'Missing data'}), 400

    user_latitude = data['latitude']
    user_longitude = data['longitude']

    # 데이터 유효성 검사 (예시)
    if not (-90 <= user_latitude <= 90) or not (-180 <= user_longitude <= 180):
        return jsonify({'error': 'Invalid latitude or longitude'}), 400

    # 사용자 위치를 배열로 변환 및 클러스터 예측
    user_location = np.array([[user_latitude, user_longitude]])
    cluster_label = kmeans.predict(user_location)[0]

    # 클러스터에 해당하는 추천 장소 검색
    recommended_places = recommendations_df[recommendations_df['cluster'] == cluster_label]['recommended_places']

    return jsonify({'cluster': cluster_label, 'recommended_places': recommended_places.tolist()})

if __name__ == '__main__':
    app.run(debug=True)  # 프로덕션 환경에서는 debug=False를 사용하거나 완전히 제거
