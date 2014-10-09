public class TestFilledBox extends TestBox {
    private boolean filled;

    public TestFilledBox() {

    }

    public TestFilledBox(int width, int height, int depth, String color, Boolean filled) {
        super(width, height, depth, color);
        this.filled = filled;
    }

    public boolean getFilled() {
        return filled;
    }

    @CSVMapperSetterMethod(fieldName = "filled")
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestFilledBox)) return false;
        if (!super.equals(o)) return false;

        TestFilledBox that = (TestFilledBox) o;

        if (filled != that.filled) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (filled ? 1 : 0);
        return result;
    }
}
