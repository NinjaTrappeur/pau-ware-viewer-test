package blcr_project_test;

public class Port {

    boolean _open = false;

    public boolean isOpen() {
        return _open;
    }

    public boolean isClosed() {
        return !_open;
    }

    public void setOpen(Boolean open) {
        _open = open;
    }
}
