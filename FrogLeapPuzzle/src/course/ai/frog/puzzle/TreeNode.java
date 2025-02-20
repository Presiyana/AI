package course.ai.frog.puzzle;

public class TreeNode {
    String state;
    TreeNode parent;

    TreeNode(String state, TreeNode parent) {
        this.state = state;
        this.parent = parent;
    }
}
