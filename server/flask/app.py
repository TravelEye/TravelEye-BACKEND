from flask import Flask, jsonify, request
from flask_cors import CORS
import os
import numpy as np
import pandas as pd
import joblib
import random  # Add this line for the random module
from sklearn.metrics.pairwise import cosine_similarity
from mysql_connector import MySqlConnector
from sklearn.preprocessing import LabelEncoder



app = Flask(__name__)
CORS(app)

# MySQL 계정정보 등록
app.config['MYSQL_HOST'] = os.environ.get('MYSQL_HOST')
app.config['MYSQL_USER'] = os.environ.get('MYSQL_USER')
app.config['MYSQL_PASSWORD'] = os.environ.get('MYSQL_PASSWORD')
app.config['MYSQL_DB'] = os.environ.get('MYSQL_DB')
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'


connector = MySqlConnector()
df,kmeans,original_df = connector.load_model_data()
@app.route('/recommend', methods=['GET'])
def get_cluster():
    traveler_id = request.args.get('traveler_id', '')
    if traveler_id and traveler_id in original_df['TRAVELER_ID'].values:
        # 클러스터링 정보는 스케일링된 데이터프레임에서 가져옵니다.
        cluster_info = df[df['TRAVELER_ID'] == traveler_id]['CLUSTER'].iloc[0]
        cluster_travelers = df[df['CLUSTER'] == cluster_info]

        if len(cluster_travelers) > 5:
            random_ids = random.sample(cluster_travelers['TRAVELER_ID'].tolist(), 5)
        else:
            random_ids = cluster_travelers['TRAVELER_ID'].tolist()

        # 원본 데이터프레임에서 선택된 TRAVELER_ID들의 상세 정보를 반환합니다.
        response_data = {'traveler_id': traveler_id, 'cluster': int(cluster_info)}
        for i, t_id in enumerate(random_ids):
            # 원본 데이터프레임에서 정보를 가져옵니다.
            traveler_data = original_df[original_df['TRAVELER_ID'] == t_id].iloc[0].to_dict()
            response_data[f'traveler_{i+1}'] = traveler_data

        return jsonify(response_data)
    else:
        return jsonify({'error': 'Traveler ID not found'}), 404


if __name__ == '__main__':
    app.run(debug=True)  # 프로덕션 환경에서는 debug=False를 사용하거나 완전히 제거
