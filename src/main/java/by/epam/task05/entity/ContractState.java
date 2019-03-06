package by.epam.task05.entity;

public enum ContractState {

    UNCONFIRMED,
    CONFIRMED,
    CANCELED,
    FINISHED;

    public static ContractState fromInteger(int value) {
        switch(value) {
            case 1:
                return ContractState.UNCONFIRMED;
            case 2:
                return ContractState.CONFIRMED;
            case 3:
                return ContractState.CANCELED;
            case 4:
                return ContractState.FINISHED;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString()
    {
        switch(this) {
            case UNCONFIRMED:
                return "unconfirmed";
            case CONFIRMED:
                return "confirmed";
            case CANCELED:
                return "canceled";
            case FINISHED:
                return "finished";
            default: throw new IllegalArgumentException();
        }
    }

    public static ContractState fromString(String value)
    {
        switch(value) {
            case "unconfirmed":
                return ContractState.UNCONFIRMED;
            case "confirmed":
                return ContractState.CONFIRMED;
            case "canceled":
                return ContractState.CANCELED;
            case "finished":
                return ContractState.FINISHED;
            default: throw new IllegalArgumentException();
        }
    }

}
