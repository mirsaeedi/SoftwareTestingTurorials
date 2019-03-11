package tutorial.core.banking.consistency;

public interface IRule<TUnit extends IUnit> {

	public Violation[] check(TUnit unit);
	
}
