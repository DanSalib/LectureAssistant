from flask import Flask, request
from flask_restful import Resource, Api
from flask_sqlalchemy import SQLAlchemy


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///C:\\Users\\DanSa\\LectureAssistant\\Python\\Database\\lectureDB.db'
db = SQLAlchemy(app)

api = Api(app)

class ExampleTable(db.Model):
	id = db.Column(db.Integer, primary_key=True)

# class HelloWorld(Resource):
# 	def get(self):
# 		return {'about' : 'HelloWorld'}

# 	def post(self):
# 		some_json = request.get_json()
# 		return {'you sent' : some_json}, 201

# class Multi(Resource):
# 	def get(self, num):
# 		return {'result' : num*10}

# api.add_resource(HelloWorld, '/')
# api.add_resource(Multi, '/multi/<int:num>')

# if __name__ == '__main__':
# 	app.run(debug=True)
