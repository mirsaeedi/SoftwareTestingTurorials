package tutorial.core.banking.models;

public class TransferResponse {

	private TransferStatus transferStatus;

	public void setTransferStatus(TransferStatus transferStatus) {
		this.transferStatus=transferStatus;
	}

	public static TransferResponse Error() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
