public class TestBox {
    private int width;
    private int height;
    private int depth;
    private String color;

    public TestBox() {
        this.width = 0;
        this.height = 0;
        this.depth = 0;
        this.color = null;
    }

    public TestBox(int width, int height, int depth, String color) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    @CSVMapperSetterMethod(fieldName = "width")
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    @CSVMapperSetterMethod(fieldName = "height")
    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    @CSVMapperSetterMethod(fieldName = "depth")
    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getColor() {
        return color;
    }

    @CSVMapperSetterMethod(fieldName = "color")
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestBox)) return false;

        TestBox testBox = (TestBox) o;

        if (depth != testBox.depth) return false;
        if (height != testBox.height) return false;
        if (width != testBox.width) return false;
        if (color != null ? !color.equals(testBox.color) : testBox.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + depth;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
