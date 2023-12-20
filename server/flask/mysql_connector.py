from dotenv import load_dotenv
import mysql.connector
import os
import joblib
import pandas as pd
from sklearn.cluster import KMeans


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

        kmeans = KMeans(n_clusters=317, random_state=42)
        scaler = KMeans(n_clusters=317, random_state=42)
        # scaled_df = KMeans(n_clusters=317, random_state=42)
        # original_df = KMeans(n_clusters=317, random_state=42)
        joblib.dump(kmeans, 'traveler_kmeans_model.pkl')
        joblib.dump(scaler, 'scaler.pkl')
        # joblib.dump(scaled_df, 'scaled_traveler.csv')
        # joblib.dump(original_df, './TravelerDB.csv')


        kmeans = joblib.load('./traveler_kmeans_model.pkl')
        scaler = joblib.load('./scaler.pkl')
        scaled_df = pd.read_csv('scaled_traveler.csv',  encoding='ISO-8859-1')
        original_df = pd.read_csv('./TravelerDB.csv', encoding='ISO-8859-1')



        if original_df.empty:
            raise self.NoDataException("No data loaded from CSV.")

        return original_df, kmeans,scaler,scaled_df

    class NoDataException(Exception):
        pass
