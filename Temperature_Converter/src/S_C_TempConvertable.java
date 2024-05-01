/**
 * Program Name:	S_C_TempConvertable.java
 * Purpose: 		Interface class that will hold some absolute zero constants and abstract methods that will be implemented in the application class.
 * @author 			Sara Chupa
 * Date:			Apr 18, 2024
 */

public interface S_C_TempConvertable
{
	// constants
	double ABS_ZERO_K = 0.0;
	double ABS_ZERO_C = -273.15;
	double ABS_ZERO_F = -459.66999999999996;
	
	// abstract methods
	public double convertFtoC(double fTemp);
	public double convertCtoF(double cTemp);
	public double convertCtoK(double cTemp);
	public double convertKtoC(double kTemp);
	public double convertFtoK(double fTemp);
	public double convertKtoF(double kTemp);
	
}
// End of class