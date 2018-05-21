import sqlite3
import datetime

now = datetime.datetime.now()

with sqlite3.connect("sample.db") as connection:
	c = connection.cursor()
	c.execute("DROP TABLE posts")
	c.execute("CREATE TABLE posts(title TEXT, dateof TEXT, data TEXT )")
	c.execute('INSERT INTO posts VALUES("Lecture 1", "%s"' %(now.strftime("%Y-%m-%d %H:%M")) + ', "this is lecture 1")')
