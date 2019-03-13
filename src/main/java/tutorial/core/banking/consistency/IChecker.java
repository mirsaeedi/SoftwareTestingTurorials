package tutorial.core.banking.consistency;

public interface IChecker<TUnit extends IUnit> {

	public Violation[] check(TUnit unit);
	
}
