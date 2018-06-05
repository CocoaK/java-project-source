package com.biencloud.smarthome.service.addressbook;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.biencloud.smarthome.service.addressbook.model.AddressBook;
import com.biencloud.smarthome.service.addressbook.service.IAddressBookService;
import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.common.model.Paging;


public class AddressBookServiceTest extends BaseTransactionalTest{
	public IAddressBookService getAddressBookService() {
		return (IAddressBookService) this.getBean("addressBookService");
	}
	@Test
	public void save()
	{
		AddressBook ab=new AddressBook("aaa", "0001", "01", "01", "01", "1010",
				"0", new Date(), "192.168.1.1", "x001",new Long(2));
		getAddressBookService().save(ab);
	}
	//@Test
	public void delete()
	{
		
		//getAddressBookService().deleteByAppId(new Long(1));
	}
	//@Test
	public void page()
	{
		
		Paging<AddressBook> page=getAddressBookService().queryAddressBookForPaging(1, 10, null, null);
		if(page.getTotalCount()>0)
		{
			assertTrue(page.getTotalCount()>0);
		}else
		{
			assertTrue(page.getTotalCount()<1);
		}
	}
}
