package com.biencloud.smarthome.web.appdata;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.biencloud.smarthome.web.appdata.json.AddressBookJson;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.appdata.service.IAddressBookService;
import com.biencloud.smarthome.web.appdata.vo.AddressBookVO;
import com.biencloud.smarthome.web.base.BaseTest;


public class AddressBookServiceTest extends BaseTest {
	public IAddressBookService getAddressBookService()
	{
		return (IAddressBookService)this.getBean("addressBookService");
	}
	@Test
	public void save()
	{
		AddressBookVO addressBook=new AddressBookVO("aabb", "0001", "01", "01", "01", "1010",
				"0", new Date(), "192.168.1.1", "x001",new Long(1));
		//getAddressBookService().saveOrUpdateAdressBook(addressBook);
	}
	@Test
	public void page()
	{
		AddressBookVO addressBook=new AddressBookVO();
		addressBook.setDeviceNo("x001");
		AddressBookJson abs=getAddressBookService().queryAddressBookForPaging(1, 10, addressBook, null);
		if(abs.getAddressBookList()!=null)
		{
			System.out.println(abs.getAddressBookList().size());
			//assertTrue(abs.getAddressBookList().size()>0);
		}else
		{
			//assertTrue(abs.getAddressBookList().size()<1);
		}
	}
	//@Test
	public void delete()
	{
		
		Json abs=getAddressBookService().deleteById("x01",new Long(2));
		if("1".equals(abs.getCode()))
		{
			assertTrue(true);
		}else
		{
			assertTrue(false);
		}
	}
}
