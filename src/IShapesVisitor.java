interface IShapesVisitor {
    void visit(CTriangleDecorator triangle);
    void visit(CCircleDecorator circle);
    void visit(CRectangleDecorator rectangle);
}
