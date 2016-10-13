
import java.util.ArrayList;

public class BinarySearch {
	public ArrayList<Data> binarySearch(ArrayList<Data> dataList, int left, int right, String columnName, String value, char operator){
		int mid,i;
		int intValue;
		ArrayList<Data> finalList = new ArrayList<Data>();
		while(left <= right)
		{
			mid=(left+right)/2;
			if(mid<0){
				break;}
			if(columnName.equals("CID")){
				intValue = Integer.parseInt(value);
				if(dataList.get(mid).getCID() == intValue)
				{
					finalList.add(new Data(dataList.get(mid).getCID(), dataList.get(mid).getFirstName(), dataList.get(mid).getLastName(), dataList.get(mid).getCity(), dataList.get(mid).getAddressLine(), dataList.get(mid).getSocialSecurityNumber()));
					return finalList;
				}
				if(mid>0){
					if((dataList.get(mid).getCID() > intValue)&&(dataList.get(mid-1).getCID()<intValue))
					{
						if(operator=='>'){
							for(i=mid;i<dataList.size();i++){
								finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
							}
							return finalList;
						}
						else if(operator=='<'){
							for(i=0;i<mid;i++){
								finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
							}
							return finalList;					}
					}
				}
				if(dataList.get(mid).getCID() > intValue)
				{
					right=mid-1;
				}
				else
				{
					left=mid+1;
				}

			}
			else if(columnName.equals("SSN")){
				intValue = Integer.parseInt(value);
				if(dataList.get(mid).getSocialSecurityNumber() == intValue)
				{
					if(operator == '<'){
						while(dataList.get(mid-1).getSocialSecurityNumber() == intValue){
							mid=mid-1;
						}
						for(i=0;i<mid;i++){
							finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
						}
					}else if(operator == '='){
						while(dataList.get(mid-1).getSocialSecurityNumber() == intValue){
							mid=mid-1;
						}
						for(i=mid;i<dataList.size();i++){
							if(dataList.get(mid).getSocialSecurityNumber() == intValue){
								finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
							}
							else{
								continue;
							}
						}
					}else if(operator == '>'){
						while(dataList.get(mid+1).getSocialSecurityNumber() == intValue){
							mid=mid+1;
						}
						for(i=mid;i<dataList.size();i++){
							finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
						}
					}
					return finalList;
				}
				if(mid>0){
					if((dataList.get(mid).getSocialSecurityNumber() > intValue)&&(dataList.get(mid-1).getSocialSecurityNumber()<intValue))
					{
						if(operator=='>'){
							for(i=mid;i<dataList.size();i++){
								finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
							}
							return finalList;					}
						else if(operator=='<'){
							for(i=0;i<mid;i++){
								finalList.add(new Data(dataList.get(i).getCID(), dataList.get(i).getFirstName(), dataList.get(i).getLastName(), dataList.get(i).getCity(), dataList.get(i).getAddressLine(), dataList.get(i).getSocialSecurityNumber()));
							}
							return finalList;					}
					}
				}
				if(dataList.get(mid).getSocialSecurityNumber() > intValue)
				{
					right=mid-1;
				}
				else
				{
					left=mid+1;
				}

			}
		}
		return null;
	}
}
