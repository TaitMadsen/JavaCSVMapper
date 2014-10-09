public class TestTransaction {
    private int amount;
    private String item;
    private TransactionEntity entity;
    private String paymentMethod;

    public TestTransaction(int amount, String item, TransactionEntity entity, String paymentMethod) {
        this.amount = amount;
        this.item = item;
        this.entity = entity;
        this.paymentMethod = paymentMethod;
    }

    public TestTransaction() {
    }

    public int getAmount() {
        return amount;
    }

    @CSVMapperSetterMethod(fieldName = "amount")
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    @CSVMapperSetterMethod(fieldName = "what was bought")
    public void setItem(String item) {
        this.item = item;
    }

    public TransactionEntity getEntity() {
        return entity;
    }

    @CSVMapperSetterMethod(fieldName = "other party")
    public void setEntity(TransactionEntity entity) {
        this.entity = entity;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    @CSVMapperSetterMethod(fieldName = "payment method")
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestTransaction)) return false;

        TestTransaction that = (TestTransaction) o;

        if (amount != that.amount) return false;
        if (entity != null ? !entity.equals(that.entity) : that.entity != null) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (paymentMethod != null ? !paymentMethod.equals(that.paymentMethod) : that.paymentMethod != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amount;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        return result;
    }
}
