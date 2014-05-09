package com.henihouse.devices;


public class MAX1038 {
    private int	   addressDevice   = 202;
    private int	   addressRegister = 0;
    private int	   writeData       = 0;
    private int	   readData	= 1;

    private I2CthroughPin i2c;

    public MAX1038(I2CthroughPin i2c) throws InterruptedException {
	this.i2c = i2c;
	i2c.stop();
    }

    public void setRegister() {
	try {

	    i2c.start();
	    i2c.write(addressDevice + writeData);
	    System.out.println("address - OK");

	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    System.out
		    .println("Error in write init register to device MAX1038.");
	}
    }
}
