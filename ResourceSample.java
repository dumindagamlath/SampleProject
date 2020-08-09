package examples;

import model.Resource;

public class ResourceSample {
    public static void main(String[] args) {
        Resource.use(
                resource -> resource
                        .doOp1()
                        .doOp2()
        );
    }
}
