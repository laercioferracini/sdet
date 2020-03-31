package pojo;

import java.util.List;

public class CustomerDetailsList{
	private List<CustomerDetails> customerDetailsList;

	public void setData(List<CustomerDetails> customerDetails){
		this.customerDetailsList = customerDetails;
	}

	public List<CustomerDetails> getData(){
		return customerDetailsList;
	}

	@Override
 	public String toString(){
		return 
			"CustomerDetailsList{" + 
			"data = '" + customerDetailsList + '\'' +
			"}";
		}
}