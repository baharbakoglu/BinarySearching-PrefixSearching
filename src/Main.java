
import java.util.*;

/*References:
 * http://www.algolist.net/Algorithms/Sorting/Quicksort
 * http://java2novice.com/java-sorting-algorithms/quick-sort/
 * http://www.tutorialspoint.com/java/java_using_comparator.htm
 * http://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
 * https://examples.javacodegeeks.com/core-java/util/comparator/java-comparator-example/
 * http://stackoverflow.com/questions/21803908/how-to-check-if-a-string-is-a-valid-integer //integer deger kontrol etmek icin
 */

public class Main {

	public static void main(String[] args) {

		ArrayList<Data> dataList = new ArrayList<Data>(); /*for data that read from data.csv*/
		ArrayList<Data> finalList = null;
		ArrayList<Data> finalListClone = null;
		ArrayList<Data> sortedList = null;

		int ID, socialSecurityNumber;
		int andCount=0, count=0;
		int invalidCommand=0;
		FileRead fr = new FileRead();
		WriteFile wf = new WriteFile();
		QuickSort qs = new QuickSort();

		BinarySearch bs = new BinarySearch();
		Scanner command=null; /*for commands that read from command.txt*/
		Scanner data=null;	/*for data that read from data.csv*/
		String tempstring;
		String[] array = null;
		String[] selectedArr = null;
		String[] valueArr=null;
		String delimiter = "\\|";
		String firstName, lastName, city,addressLine;

		command = fr.openFile(args[0]); /*open file command.txt*/
		data =fr.openFile(args[1]);     /*open file data.csv*/
		wf.openWFile("output.txt");		/*open file output.txt*/
		tempstring = data.nextLine();	/*read line*/
		int i=0,j=0;
		while(data.hasNext())
		{
			tempstring = data.nextLine();
			array = tempstring.split(delimiter);
			ID = Integer.parseInt(array[0]); /*CIDs converting to int from string*/
			firstName = array[1];
			lastName = array[2];
			city = array[3];
			addressLine = array[4];
			socialSecurityNumber = Integer.parseInt(array[5]);

			dataList.add(new Data(ID, firstName, lastName, city, addressLine, socialSecurityNumber));

		}

		while(command.hasNext()) /*We keep a list of the read data*/
		{
			count=0;
			andCount=0;
			invalidCommand=0;
			tempstring = command.nextLine();
			wf.writeln("CommandText: \""+tempstring+"\"");
			wf.writeln(" ");
			wf.writeln("Results:");
			array = tempstring.split(" "); /*split line according to space*/
			selectedArr = array[1].split(","); /*split array elements according to comma*/
			for(i=0;i<array.length;i++)
			{
				if(array [i].equals("AND")){ /*calculate AND count the command*/
					andCount++;
				}
			}
			for(i=0;i<=2*andCount;i=i+2)
			{
				if(array[3+i].indexOf('~')!=-1) /*search for ~ */
				{
					valueArr = array[3+i].split("~"); /*split according to ~ */
					if(count==0){
						sortedList = new ArrayList<Data>(dataList);
						if(valueArr[0].equals("FirstName")){/*Quick sort according to FirstName*/
							qs.quickSort(sortedList, "FN", 0, sortedList.size()-1);
							finalList = StartWithFunction(wf,sortedList, 0, sortedList.size()-1, valueArr[1], "FN");
						}else if(valueArr[0].equals("LastName")){/*Quick sort according to LastName*/
							qs.quickSort(sortedList, "LN", 0, sortedList.size()-1);
							finalList = StartWithFunction(wf,sortedList, 0, sortedList.size()-1, valueArr[1], "LN");
						}else if(valueArr[0].equals("City")){/*Quick sort according to City*/
							qs.quickSort(sortedList, "CITY", 0, sortedList.size()-1);
							finalList = StartWithFunction(wf,sortedList, 0, sortedList.size()-1, valueArr[1], "CITY");
						}else if(valueArr[0].equals("AddressLine1")){/*Quick sort according to AddressLine1*/
							qs.quickSort(sortedList, "AL", 0, sortedList.size()-1);
							finalList = StartWithFunction(wf,sortedList, 0, sortedList.size()-1, valueArr[1], "AL");
						}
						if(finalList!=null){
							finalListClone = new ArrayList<>(finalList);
						}
						count++;
					}
					else {
						if(valueArr[0].equals("FirstName")){
							qs.quickSort(finalListClone, "FN", 0, finalListClone.size()-1);
							finalList = StartWithFunction(wf,finalListClone, 0, finalListClone.size()-1, valueArr[1], "FN");
						}else if(valueArr[0].equals("LastName")){
							qs.quickSort(sortedList, "LN", 0, finalListClone.size()-1);
							finalList = StartWithFunction(wf,finalListClone, 0, finalListClone.size()-1, valueArr[1], "LN");
						}else if(valueArr[0].equals("City")){
							qs.quickSort(finalListClone, "CITY", 0, finalListClone.size()-1);
							finalList = StartWithFunction(wf,finalListClone, 0, finalListClone.size()-1, valueArr[1], "CITY");
						}else if(valueArr[0].equals("AddressLine1")){
							qs.quickSort(finalListClone, "AL", 0, finalListClone.size()-1);
							finalList = StartWithFunction(wf,finalListClone, 0, finalListClone.size()-1, valueArr[1], "AL");
						}
						if(finalList!=null){
							finalListClone = new ArrayList<>(finalList);
						}
						}
				}
				else {
					if(array[3+i].indexOf('<')!=-1)
					{
						valueArr = array[3+i].split("<");
						//buyukluk kucukluk ve esitliklerde girilen deger integer mi kontrolunu try catch blogunda yaptim
						try {
						    j = Integer.parseInt(valueArr[1]);
						} catch (NumberFormatException e) {
						    invalidCommand = 1;
						    break;
						}
						if(count==0){
							sortedList = new ArrayList<Data>(dataList);
							if(valueArr[0].equals("CID")){

								qs.quickSort(sortedList, "CID", 0, sortedList.size()-1);
								finalList = bs.binarySearch(sortedList, 0, sortedList.size(), "CID", valueArr[1], '<');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}else if(valueArr[0].equals("SocialSecurityNumber")){

								qs.quickSort(sortedList, "SSN", 0, sortedList.size()-1);
								finalList = bs.binarySearch(sortedList, 0, sortedList.size(), "SSN", valueArr[1], '<');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}							}
							else if(valueArr[0].equals("FirstName") || valueArr[0].equals("LastName") || valueArr[0].equals("City") || valueArr[0].equals("AddressLine1")){
								invalidCommand=1;

							}
							count++;
						}
						else {
							if(valueArr[0].equals("CID")){
								qs.quickSort(finalListClone, "CID", 0, finalListClone.size()-1);
								finalList = bs.binarySearch(finalListClone, 0, finalListClone.size(), "CID", valueArr[1], '<');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}else if(valueArr[0].equals("SocialSecurityNumber")){
								qs.quickSort(finalListClone, "SSN", 0, finalListClone.size()-1);
								finalList = bs.binarySearch(finalListClone, 0, finalListClone.size(), "SSN", valueArr[1], '<');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}
							else if(valueArr[0].equals("FirstName") || valueArr[0].equals("LastName") || valueArr[0].equals("City") || valueArr[0].equals("AddressLine1")){
								invalidCommand=1;
							}

						}
					}else if(array[3+i].indexOf('>')!=-1)
					{
						valueArr = array[3+i].split(">");
						try {
						    j = Integer.parseInt(valueArr[1]);
						} catch (NumberFormatException e) {
						    invalidCommand = 1;
						    break;
						}
						if(count==0){
							sortedList = new ArrayList<Data>(dataList);
							if(valueArr[0].equals("CID")){
								qs.quickSort(sortedList, "CID", 0, sortedList.size()-1);
								finalList = bs.binarySearch(sortedList, 0, sortedList.size(), "CID", valueArr[1], '>');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}else if(valueArr[0].equals("SocialSecurityNumber")){
								qs.quickSort(sortedList, "SSN", 0, sortedList.size()-1);
								finalList = bs.binarySearch(sortedList, 0, sortedList.size(), "SSN", valueArr[1], '>');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
								}
							else if(valueArr[0].equals("FirstName") || valueArr[0].equals("LastName") || valueArr[0].equals("City") || valueArr[0].equals("AddressLine1")){
								invalidCommand=1;
							}
							count++;

						}
						else {
							if(valueArr[0].equals("CID")){
								qs.quickSort(finalListClone, "CID", 0, finalListClone.size()-1);
								finalList = bs.binarySearch(finalListClone, 0, finalListClone.size(), "CID", valueArr[1], '>');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}else if(valueArr[0].equals("SocialSecurityNumber")){
								qs.quickSort(finalListClone, "SSN", 0, finalListClone.size()-1);
								finalList = bs.binarySearch(finalListClone, 0, finalListClone.size(), "SSN", valueArr[1], '>');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}
							else if(valueArr[0].equals("FirstName") || valueArr[0].equals("LastName") || valueArr[0].equals("City") || valueArr[0].equals("AddressLine1")){
								invalidCommand=1;
							}

						}
					}else if(array[3+i].indexOf('=')!=-1)
					{
						valueArr = array[3+i].split("=");
						try {
						    j = Integer.parseInt(valueArr[1]);
						} catch (NumberFormatException e) {
						    invalidCommand = 1;
						    break;
						}
						if(count==0){
							sortedList = new ArrayList<Data>(dataList);
							if(valueArr[0].equals("CID")){
								qs.quickSort(sortedList, "CID", 0, sortedList.size()-1);
								finalList = bs.binarySearch(sortedList, 0, sortedList.size(), "CID", valueArr[1], '=');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}else if(valueArr[0].equals("SocialSecurityNumber")){
								qs.quickSort(sortedList, "SSN", 0, sortedList.size()-1);
								finalList = bs.binarySearch(sortedList, 0, sortedList.size(), "SSN", valueArr[1], '=');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
								}

							else if(valueArr[0].equals("FirstName") || valueArr[0].equals("LastName") || valueArr[0].equals("City") || valueArr[0].equals("AddressLine1")){
								invalidCommand=1;
							}
							count++;
						}
						else {
							if(valueArr[0].equals("CID")){
								qs.quickSort(finalListClone, "CID", 0, finalListClone.size()-1);
								finalList = bs.binarySearch(finalListClone, 0, finalListClone.size(), "CID", valueArr[1], '=');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}else if(valueArr[0].equals("SocialSecurityNumber")){
								qs.quickSort(finalListClone, "SSN", 0, finalListClone.size()-1);
								finalList = bs.binarySearch(finalListClone, 0, finalListClone.size(), "SSN", valueArr[1], '=');
								if(finalList!=null){
									finalListClone = new ArrayList<>(finalList);
								}
							}
							else if(valueArr[0].equals("FirstName") || valueArr[0].equals("LastName") || valueArr[0].equals("City") || valueArr[0].equals("AddressLine1")){
								invalidCommand=1;
							}

						}
					}

				}
			}
			if((invalidCommand!=1)&&(finalList!=null)){
				for(j=0;j<selectedArr.length;j++){
					System.out.print(selectedArr[j]+"\t");
					if(selectedArr[j].equals("CID")){
						wf.write(selectedArr[j]+"\t\t");

					}
					else if(selectedArr[j].equals("FirstName")){
						wf.write(selectedArr[j]+"\t\t");
					}
					else if(selectedArr[j].equals("LastName")){
						wf.write(selectedArr[j]+"\t\t\t");
					}
					else if(selectedArr[j].equals("City")){
						wf.write(selectedArr[j]+"\t\t\t\t");
					}
					else if(selectedArr[j].equals("AddressLine1")){
						wf.write(selectedArr[j]+"\t\t\t\t");
					}
					else if(selectedArr[j].equals("SocialSecurityNumber")){
						wf.write(selectedArr[j]+"\t\t");
					}

				}
				System.out.println();
				wf.writeln(" ");

				for(i=0;i<finalList.size();i++){
					for(j=0;j<selectedArr.length;j++){
						if(selectedArr[j].equals("CID")){
							System.out.print(finalList.get(i).getCID()+"\t");
							wf.write(finalList.get(i).getCID()+"\t\t");
						}
						else if(selectedArr[j].equals("FirstName")){
							System.out.print(finalList.get(i).getFirstName()+"\t");
							wf.write(finalList.get(i).getFirstName()+"\t\t\t");
						}
						else if(selectedArr[j].equals("LastName")){
							System.out.print(finalList.get(i).getLastName()+"\t");
							wf.write(finalList.get(i).getLastName()+"\t\t\t\t");
						}
						else if(selectedArr[j].equals("City")){
							System.out.print(finalList.get(i).getCity()+"\t");
							wf.write(finalList.get(i).getCity()+"\t\t\t\t");
						}
						else if(selectedArr[j].equals("AddressLine1")){
							System.out.print(finalList.get(i).getAddressLine()+"\t");
							wf.write(finalList.get(i).getAddressLine()+"\t\t\t\t");
						}
						if(selectedArr[j].equals("SocialSecurityNumber")){
							System.out.print(finalList.get(i).getSocialSecurityNumber()+"\t");
							wf.write(finalList.get(i).getSocialSecurityNumber()+"\t\t");
						}
					}
					System.out.println();
					wf.writeln(" ");
					}
			}
			if(invalidCommand==1){
				wf.writeln("Invalid command!");
			}
			if(finalList==null){
				wf.writeln("Empty rowset");
			}
			wf.writeln(" ");
		}

		fr.closeFile(data);//close file
	}
	static ArrayList<Data> StartWithFunction(WriteFile wf,ArrayList<Data> dataList,int indexLeft, int indexRight,String value, String columnName)
	{

		ArrayList<Data> finalList = new ArrayList<Data>();
		for(int i=indexLeft;i<indexRight+1;i++)
		{
			if(columnName == "FN"){
				if((dataList.get(i).getFirstName().startsWith(value)==true)||(dataList.get(i).getFirstName().startsWith(value.toUpperCase())==true))
				{
					//System.out.println(dataList.get(i).getFirstName());
					finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
				}
			}else if(columnName == "LN"){
				if((dataList.get(i).getLastName().startsWith(value)==true)||(dataList.get(i).getLastName().startsWith(value.toUpperCase())==true))
				{
					//System.out.println(dataList.get(i).getLastName());
					//wf.writeln(dataList.get(i).getLastName());
					finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
				}
			}else if(columnName == "CITY"){
				if((dataList.get(i).getCity().startsWith(value)==true)||(dataList.get(i).getCity().startsWith(value.toUpperCase())==true))
				{
					//System.out.println(dataList.get(i).getCity());
					//wf.writeln(dataList.get(i).getCity());
					finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
				}
			}else if(columnName == "AL"){
				if((dataList.get(i).getAddressLine().startsWith(value)==true)||(dataList.get(i).getAddressLine().startsWith(value.toUpperCase())==true))
				{
					//System.out.println(dataList.get(i).getAddressLine());
					//wf.writeln(dataList.get(i).getAddressLine());
					finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
				}
			}
		}
		return finalList;
	}


}
