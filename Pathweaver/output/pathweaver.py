import sys
import csv

if __name__ == '__main__':
	if len(sys.argv) != 3:
		print("Invalid number of arguments.")
		print("Usage: pathweaver.py filename classname")
		exit(1)

	filename = sys.argv[1]
	classname = sys.argv[2]

	with open(filename) as csvfile:
		reader = csv.reader(csvfile, delimiter=',')
		java_str = "\npublic class " + classname + " {\n"
		java_str += "\t// Position, velocity, acceleration, duration\n"
		java_str += "\tpublic static double[][] points = new double[][]{\n"
		iterreader = iter(reader)
		next(reader)
		for row in iterreader:
			java_str += "\t\t{"
			java_str += f" {row[3]}, {row[4]}, {row[5]}, {row[0]} "
			java_str += "},\n"
		java_str += "\t};\n"
		java_str += "}\n"
		print(java_str)
