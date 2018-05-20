import sqlite3 as lite
import sys

lectureDB = (
  ('lec1', 'May 15, 2018'),
  ('lec2', 'May 16, 2018')
)

con = lite.connect('lectureDB.db')

with con:
	cur = con.cursor()

	cur.execute("DROP TABLE IF EXISTS reps")
	cur.execute("CREATE TABLE reps(rep_name TEXT, rep_data TEXT)")
	cur.executemany("INSERT INTO reps VALUES(?, ?)", lectureDB)