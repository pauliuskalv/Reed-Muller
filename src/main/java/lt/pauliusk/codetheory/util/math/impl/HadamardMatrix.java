package lt.pauliusk.codetheory.util.math.impl;

import lt.pauliusk.codetheory.util.math.IHadamardMatrix;
import lt.pauliusk.codetheory.util.math.IKroeneckerProduct;
import lt.pauliusk.codetheory.util.math.IMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HadamardMatrix implements IHadamardMatrix {
    @Autowired
    private IKroeneckerProduct mKroeneckerProduct;

    private IMatrix mBase = new Matrix(
            new int[][] {
                    {1, 1},
                    {1, -1}
            }
    );

    @Override
    public IMatrix generate(int i, int m) {
        int I1 = (int) Math.pow(2, m - i);
        int I2 = (int) Math.pow(2, i - 1);

        IMatrix res = mKroeneckerProduct.generateBase(mBase, I1);
        res = mKroeneckerProduct.generate(res, I2);

        return res;
    }
}
