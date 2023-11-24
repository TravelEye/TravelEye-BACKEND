from flask import Flask, jsonify, request
from flask_cors import CORS
import pandas as pd
import random
import os
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.cluster import KMeans
from mysql_connector import MySqlConnector, NoDataException

app = Flask(__name__)
CORS(app)

# MySQL 계정정보 등록
app.config['MYSQL_HOST'] = os.environ.get('MYSQL_HOST')
app.config['MYSQL_USER'] = os.environ.get('MYSQL_USER')
app.config['MYSQL_PASSWORD'] = os.environ.get('MYSQL_PASSWORD')
app.config['MYSQL_DB'] = os.environ.get('MYSQL_DB')
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'

mysql = MySqlConnector()

def load_model_data():
    df = pd.read_csv('./traveller.csv', encoding='UTF8')

    # 'GENDER' 열의 문자열을 숫자 형식으로 변환
    df['GENDER'] = LabelEncoder().fit_transform(df['GENDER'])

    # 스케일링을 적용할 열 선택
    scaler = StandardScaler()
    scaled_columns = ['GENDER', 'AGE_GRP', 'INCOME'] + [col for col in df.columns if col.startswith('TRAVEL_STYL_')]
    df[scaled_columns] = scaler.fit_transform(df[scaled_columns])

    # K-means 클러스터링
    n_clusters = 5
    kmeans = KMeans(n_clusters=n_clusters, random_state=42)
    df['CLUSTER'] = kmeans.fit_predict(df[scaled_columns])

    return df, kmeans

df, kmeans_model = load_model_data()

@app.route('/')
def index():
    return "Welcome to my Flask app!"

@app.route('/get_cluster', methods=['GET'])
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
    app.run(debug=True, use_reloader=False, port=5001)
