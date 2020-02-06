package TestData;

import com.codoid.products.fillo.Recordset;

import Common.ExcelUtil;

public class CreateIdeaTestData {
	
private String PNo,	IntiatorName,	DirectSupervisore,	ST,	Benfit,	Category;

public String getPNo() {
	return PNo;
}

public void setPNo(String pNo) {
	PNo = pNo;
}

public String getIntiatorName() {
	return IntiatorName;
}

public void setIntiatorName(String intiatorName) {
	IntiatorName = intiatorName;
}

public String getDirectSupervisore() {
	return DirectSupervisore;
}

public void setDirectSupervisore(String directSupervisore) {
	DirectSupervisore = directSupervisore;
}

public String getST() {
	return ST;
}

public void setST(String sT) {
	ST = sT;
}

public String getBenfit() {
	return Benfit;
}

public void setBenfit(String benfit) {
	Benfit = benfit;
}

public String getCategory() {
	return Category;
}

public void setCategory(String category) {
	Category = category;
}



@Override
public String toString() {
	return "CreateIdeaTestData [PNo=" + PNo + ", IntiatorName=" + IntiatorName + ", DirectSupervisore="
			+ DirectSupervisore + ", ST=" + ST + ", Benfit=" + Benfit + ", Category=" + Category + "]";
}

public void GetData(String TestID) throws Exception{
	//We need to goto excel sheet and fetch data based on the TestID
	Recordset rs=ExcelUtil.GetTestData("CreateIdea_TestData", TestID);
	
	while(rs.next()) {
	this.setPNo(rs.getField("PNo"));	
	this.setIntiatorName(rs.getField("IntiatorName"));
	this.setDirectSupervisore(rs.getField("DirectSupervisore"));
	this.setST(rs.getField("ST"));
	this.setBenfit(rs.getField("Benfit"));
	this.setCategory(rs.getField("Category"));
	
	}
	rs.close();
	
	
	
	
}

}
