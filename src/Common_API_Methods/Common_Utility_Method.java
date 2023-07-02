package Common_API_Methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Common_Utility_Method {
public static void EvidenceCreator(String Filename, String RequestBody,String ResponseBody, int StatusCode) throws IOException {
		
		File TextFile = new File("C:\\Users\\HP\\Downloads\\FileRestAssured\\"+Filename+".txt");
		//System.out.println("New blank text file of name : " +TextFile.getName());
		
		FileWriter dataWrite = new FileWriter(TextFile);
	
	    dataWrite.write("Request Body is : " +RequestBody +"\n\n");
	    dataWrite.write("Status Code is : "+StatusCode + "\n\n");
	    dataWrite.write("ResponseBody is : "+ResponseBody);
	    
	    dataWrite.close();
	    //System.out.println("Request body and Response body written in textfile: " + TextFile.getName());
	    }
	public static ArrayList<String> ReadDataExcel(String sheetname, String TestCaseName) throws IOException
	{
		ArrayList <String> ArrayData = new ArrayList<String>();
		//Create the object of file input stream to locate the file
		FileInputStream Fis = new FileInputStream("D:\\SeleniumNotes\\DataExcelSheet.xlsx");
	    
		//Step2: Open the excel file by creating the object of XSSFWorkBook
		XSSFWorkbook WorkBook = new XSSFWorkbook(Fis);
   
		//Step3: Open the desired sheet 
		int countofsheet = WorkBook.getNumberOfSheets();
		for(int i=0 ; i<countofsheet ; i++) {
		
			String SheetName = WorkBook.getSheetName(i);
		
		// Access the desired sheet
		    if(SheetName.equalsIgnoreCase(sheetname))
		    {
		    	//Use XSSFSheet to save the sheet into a variable
		        XSSFSheet Sheet	= WorkBook.getSheetAt(i);
		        
		        //Create iterator to iterate through rows and find out in which column found
		        Iterator<Row> Rows = Sheet.iterator();
		        Row FirstRow = Rows.next();
		        
		        //Create the iterator to iterate through cells of first row to find out  which cell contains test case name
		         Iterator<Cell> CellOfFirstRow = FirstRow.cellIterator();
		         int k=0;
		         int TC_column=0;
		         while(CellOfFirstRow.hasNext())
		         {
		        	 Cell CellValue = CellOfFirstRow.next();
		        	 if(CellValue.getStringCellValue().equalsIgnoreCase("TestCaseName"))
		        		 
                     {
		        		 
	                    TC_column = k;
	                    //System.out.println("expected column for test case name:" +k);
	                    break;
                     }
                     k++;
		         }
		         //Verify the row where the desired test case is found and fetch the entire row 
		         while(Rows.hasNext())
		         {
		        	 Row DataRow = Rows.next();
		        	 String TCName = DataRow.getCell(TC_column).getStringCellValue();
		             if (TCName.equalsIgnoreCase(TestCaseName))
		             {
		            	 Iterator<Cell> CellValues = DataRow.cellIterator();
		            	 while(CellValues.hasNext())
		            	 {
		            		 String Data = "";
								Cell CurrentCell = CellValues.next();
								try
								{
									String StringData = CurrentCell.getStringCellValue();
									Data = StringData;
								}
								catch(IllegalStateException e)
								{
								    double doubledata = CurrentCell.getNumericCellValue();
								    Data = Double.toString(doubledata);
								}
								
								ArrayData.add(Data);
							}
							break;
						}
					}

				}
			}
			return ArrayData;
	}
}
							
		            	 
		            		/* String Data = CellValues.next().getStringCellValue();(for only String value validation for own understanding)
		            		 ArrayData.add(Data);
		            	 }
		             }
		         }
		         
		         
		         
		    }
		    
		}
		return ArrayData;*/

