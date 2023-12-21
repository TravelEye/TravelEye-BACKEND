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
df,kmeans = connector.load_model_data()
@app.route('/recommend', methods=['GET'])
def get_cluster():
    traveler_id = request.args.get('traveler_id', '')
    if traveler_id and traveler_id in df['TRAVELER_ID'].values:
        cluster_info = df[df['TRAVELER_ID'] == traveler_id]['CLUSTER'].iloc[0]

        # 해당 TRAVELER_ID의 클러스터에 속하는 다른 TRAVELER_ID를 무작위로 5개 선택
        cluster_travelers = df[df['CLUSTER'] == cluster_info]['TRAVELER_ID'].tolist()
        if len(cluster_travelers) > 5:
            random_ids = random.sample(cluster_travelers, 5)
        else:
            random_ids = cluster_travelers

        return jsonify(
            {'traveler_id': traveler_id, 'cluster': int(cluster_info), 'other_travelers_in_cluster': random_ids})
    else:
        return jsonify({'error': 'Traveler ID not found'}), 404


if __name__ == '__main__':
    app.run(debug=True)  # 프로덕션 환경에서는 debug=False를 사용하거나 완전히 제거
