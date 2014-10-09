public class TransactionEntity {
    private String name;
    private long identificationCode;

    public TransactionEntity(String name, long identificationCode) {
        this.name = name;
        this.identificationCode = identificationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(long identificationCode) {
        this.identificationCode = identificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionEntity)) return false;

        TransactionEntity that = (TransactionEntity) o;

        if (identificationCode != that.identificationCode) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (identificationCode ^ (identificationCode >>> 32));
        return result;
    }
}
