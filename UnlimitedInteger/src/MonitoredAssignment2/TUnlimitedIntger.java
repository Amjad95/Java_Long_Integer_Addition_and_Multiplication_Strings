package MonitoredAssignment2;

public class TUnlimitedIntger {
	public static void main(String[] args) {
	
		UnlimitedInteger op1 = new UnlimitedInteger("12");
		UnlimitedInteger op2 = op1.times(new UnlimitedInteger("4"));
		
		System.out.println(op1 + "" +  op2);
		
	}
}
