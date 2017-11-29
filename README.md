# ShortHandDB
Get rid of those create table quires in android and get a neat and clean code.

NB: This is my first attempt to create a library for android. I have created for my  own benefit and sharing it if some else find it useful.

## Installation
Add it in your **root** build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.Sp4Rx:shorthanddb:1.0'
	}

## Code Example

Generate the create table SQL

        //Method 1
        Map<String, DataType> tableStructure = new HashMap<>();
        tableStructure.put(KEY_USER_EMAIL, DataType.TEXT);
        tableStructure.put(KEY_USER_ID, DataType.INTEGER);

        Log.d(TAG, Table.generateSql(TABLE_USER, tableStructure));

        //Method 2
        ShortHandSchema shortHandSchema = new ShortHandSchema() {
            @Override
            public ArrayList<Schema> getSchema() {
                ArrayList<Schema> schemas = new ArrayList<>();
                schemas.add(new Schema(KEY_USER_ID, 
                new Constraint[]{Constraint.PRIMARY_KEY, Constraint.UNIQUE}, 
                DataType.INTEGER));
                schemas.add(new Schema(KEY_USER_EMAIL, DataType.TEXT));
                return schemas;
            }
        };
        Log.d(TAG, Table.generateSql(TABLE_USER, shortHandSchema));
You can also pass the database object to create the table directly

        //Method 3
        Map<String, DataType> tableStructure = new HashMap<>();
        tableStructure.put(KEY_USER_EMAIL, DataType.TEXT);
        tableStructure.put(KEY_USER_ID, DataType.INTEGER);

        Table.create(TABLE_USER, tableStructure, db);

        //Method 4
        ShortHandSchema shortHandSchema = new ShortHandSchema() {
            @Override
            public ArrayList<Schema> getSchema() {
                ArrayList<Schema> schemas = new ArrayList<>();
                schemas.add(new Schema(KEY_USER_ID,
                 new Constraint[]{Constraint.PRIMARY_KEY, Constraint.UNIQUE},
                 DataType.INTEGER));
                schemas.add(new Schema(KEY_USER_EMAIL, DataType.TEXT));
                return schemas;
            }
        };
        Table.create(TABLE_USER_2, shortHandSchema, db);

## License

      MIT License
    
    Copyright (c) 2017 Suvajit Sarkar
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
