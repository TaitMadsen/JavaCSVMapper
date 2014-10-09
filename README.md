JavaCSVMapper
=============

A Java utility for mapping lines in a CSV file to objects.

To use, add @CSVMapperSetterMethod annotation to setter methods in your destination object that correspond 
with the fields you want to obtain from your CSV.  There are a few simple rules:  

* Your CSV must have column names in the first row.  
* The setter methods do not have to be in the same order as the fields in the CSV
* The @CSVMapperSetterMethod annotation requires a 'fieldName' argument which must match the name of the desired column
* These setter methods' input must be a String, or any type with a 'valueOf(String s)' method.  Primitives are allowed.
* The receiving class (that you are trying to build, that has the above annotations applied to its setters) must have a constructor that takes zero arguments so that it can be built dynamically.

That's it!  For a usage example, see tst/TestBox.java and tst/testBoxes.txt.  Their associated use case can be found in the primary test file: tst/MapperTest.java.
