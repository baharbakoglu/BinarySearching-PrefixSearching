import java.util.ArrayList;



public class QuickSort {

	int partition(ArrayList<Data> DataList, String columnName, int left, int right)
	{
	      int i = left, j = right;
	      int pivotInt;
	      String pivotString;
	      StrComparator cmp = new StrComparator();
	      if(columnName.equals("CID")){
	    	  pivotInt = DataList.get((left + right) / 2).getCID();

		      while (i <= j) {
		            while (DataList.get(i).getCID() < pivotInt){
		                i++;
		            }
		            while (DataList.get(j).getCID() > pivotInt){
		                  j--;
		            }
		            if (i <= j) {
		                  exchangeNumbers(DataList,i,j);
		                  i++;
		                  j--;
		            }
		      }
	      }
	      else if(columnName.equals("SSN"))/* SSN means Social Security Number*/
	      {
	    	  pivotInt = DataList.get((left + right) / 2).getSocialSecurityNumber();

		      while (i <= j) {
		            while (DataList.get(i).getSocialSecurityNumber() < pivotInt){
		                i++;
		            }
		            while (DataList.get(j).getSocialSecurityNumber() > pivotInt){
		                  j--;
		            }
		            if (i <= j) {
		                  exchangeNumbers(DataList,i,j);
		                  i++;
		                  j--;
		            }
		      }
	      }
	      else if(columnName.equals("FN"))/*FN means First Name*/
	      {
	    	  pivotString = DataList.get((left + right) / 2).getFirstName();

		      while (i <= j) {
		            while ((cmp.compare(DataList.get(i).getFirstName(), pivotString))<0){
		                i++;
		            }
		            while ((cmp.compare(DataList.get(j).getFirstName(), pivotString))>0){
		                j--;
		            }
		            if (i <= j) {
		                  exchangeNumbers(DataList,i,j);
		                  i++;
		                  j--;
		            }
		      }
	      }
	      else if(columnName.equals("LN"))/*LN means Last Name*/
	      {
	    	  pivotString = DataList.get((left + right) / 2).getLastName();

		      while (i <= j) {
		            while ((cmp.compare(DataList.get(i).getLastName(), pivotString))<0){
		                i++;
		            }
		            while ((cmp.compare(DataList.get(j).getLastName(), pivotString))>0){
		                j--;
		            }
		            if (i <= j) {
		                  exchangeNumbers(DataList,i,j);
		                  i++;
		                  j--;
		            }
		      }
	      }
	      else if(columnName.equals("CITY"))
	      {
	    	  pivotString = DataList.get((left + right) / 2).getCity();

		      while (i <= j) {
		            while ((cmp.compare(DataList.get(i).getCity(), pivotString))<0){
		                i++;
		            }
		            while ((cmp.compare(DataList.get(j).getCity(), pivotString))>0){
		                j--;
		            }
		            if (i <= j) {
		                  exchangeNumbers(DataList,i,j);
		                  i++;
		                  j--;
		            }
		      }
	      }
	      else if(columnName.equals("AL"))/* AL means Address Line*/
	      {
	    	  pivotString = DataList.get((left + right) / 2).getAddressLine();

		      while (i <= j) {
		            while ((cmp.compare(DataList.get(i).getAddressLine(), pivotString))<0){
		                i++;
		            }
		            while ((cmp.compare(DataList.get(j).getAddressLine(), pivotString))>0){
		                j--;
		            }
		            if (i <= j) {
		                  exchangeNumbers(DataList,i,j);
		                  i++;
		                  j--;
		            }
		      }
	      }
	      return i;
	}

	 private void exchangeNumbers(ArrayList<Data> DataList,int i, int j) {
	        Data temp = DataList.get(i);
	        DataList.set(i,DataList.get(j));
	        DataList.set(j,temp);
	    }

	void quickSort(ArrayList<Data> DataList, String columnName, int left, int right) {
	      int index = partition(DataList, columnName,left, right);
	      if (left < index - 1)
	            quickSort(DataList,columnName, left, index - 1);
	      if (index < right)
	            quickSort(DataList,columnName, index, right);
	}






}
