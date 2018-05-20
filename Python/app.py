from flask import Flask, render_template, redirect, url_for, request, session, flash, g
from flask_restful import Resource, Api
import sqlite3

app = Flask(__name__)

app.host = '0.0.0.0'

app.secret_key = "super secret"

app.database = "sample.db"

api = Api(app)
  
class getLectures(Resource):
	def get(self):
		g.db = connect_db()
		cur = g.db.execute('select * from posts')
		posts = [dict(title=row[0], description=row[1]) for row in cur.fetchall()]
		g.db.close()
		return posts

	def post(self):
		some_json = request.get_json()
		return {'you sent' : some_json}, 201

class Multi(Resource):
	def post(self, text1, text2):
		with sqlite3.connect("sample.db") as connection:
			c = connection.cursor()
			c.execute('INSERT INTO posts VALUES("%s"' %text1+ ', "%s"' %text2 + ')')
			cur = c.execute('select * from posts')
			posts = [dict(title=row[0], description=row[1]) for row in cur.fetchall()]
			c.close()
			return posts

def connect_db():
	return sqlite3.connect(app.database)

api.add_resource(getLectures, '/')
api.add_resource(Multi, '/multi/<string:text1>/<string:text2>')

if __name__ == '__main__':
	app.run(debug=True, host='192.168.2.16')
