# Java Test Harness

This is a Java application used to test other Java classes.  It uses
Java "reflection" and it works like this:

1. It looks into the folder "/tests" to see if it can find Java .java files
2. It prints a list of these files
3. The user can select a file
4. This application will compile that file then execute the "testDB" method

### Benefits for instructors:

- the instructor of a course with a large number of students can
easily test the code of many students
- the students get exposure to some cool Java things they might not
otherwise see like reflection, plus they must be aware that their
source code must not just work in THEIR applications but in OTHER PEOPLE's
applications!

## Table of Contents

- [Installation](#installation)
- [Javadocs](#javadocs)
- [Authors](#authors)
- [License](#license)
- [CHANGELOG.md](CHANGELOG.md)


## Changelog

For a detailed list of changes and version history, please refer to the [CHANGELOG.md](CHANGELOG.md) file.

## Installation

(1) No installation necessary.  Just be sure that new Java ".java" files to 
test are located in the "/tests" subdirectory.
(2) 2 drivers are provided (SQLite, PostgreSQL) but these can easily be
replaced if desired.

## Javadocs

Javadocs are the built-in documentation system in Java, so that with one
command you can create beautiful HTML files that show your source code
documentation.

This application does NOT use Javadocs

## Authors

Your friendly, neighborhood Dr. Ken and his little green friend

## License

Never operate a motor vehicle without the proper license.