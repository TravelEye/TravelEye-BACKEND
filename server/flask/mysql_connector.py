from dotenv import load_dotenv
import mysql.connector
import os
import joblib
import pandas as pd
from sklearn.cluster import KMeans

from sklearn.preprocessing import StandardScaler,LabelEncoder
load_dotenv()

class MySqlConnector:

    def __init__(self):
        self.conn = mysql.connector.connect(
            host=os.getenv("MYSQL_HOST"),
            user=os.getenv("MYSQL_USER"),
            password=os.getenv("MYSQL_PASSWORD"),
            database=os.getenv("MYSQL_DB"),
            port=os.getenv("MYSQL_PORT"),
        )
        if self.conn is None:
            raise ValueError("Failed to establish a connection with the MySQL database.")

    def load_model_data(self):  # Added 'self' parameter

        # kmeans = KMeans(n_clusters=317, random_state=42)
        # scaler = KMeans(n_clusters=317, random_state=42)
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



    class NoDataException(Exception):
        pass
