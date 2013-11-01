/*   Copyright 2013 Juan Rada-Vilela

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.fuzzylite.term;

import com.fuzzylite.Op;
import static com.fuzzylite.Op.str;

/**
 *
 * @author jcrada
 */
public class PiShape extends Term {

    protected double bottomLeft, topLeft;
    protected double topRight, bottomRight;

    public PiShape(String name) {
        this(name, Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    }

    public PiShape(String name, double bottomLeft, double topLeft,
            double topRight, double bottomRight) {
        this.name = name;
        this.bottomLeft = bottomLeft;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
    }

    @Override
    public double membership(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        }
        //from Octave smf.m
        double a_b_ave = (bottomLeft + topLeft) / 2.0;
        double b_minus_a = topLeft - bottomLeft;
        double c_d_ave = (topRight + bottomRight) / 2.0;
        double d_minus_c = bottomRight - topRight;

        if (Op.isLE(x, bottomLeft)) {
            return 0.0;
        } else if (Op.isLE(x, a_b_ave)) {
            return 2.0 * Math.pow((x - bottomLeft) / b_minus_a, 2);
        } else if (Op.isLt(x, topLeft)) {
            return 1.0 - 2.0 * Math.pow((x - topLeft) / b_minus_a, 2);
        } else if (Op.isLE(x, topRight)) {
            return 1;
        } else if (Op.isLE(x, c_d_ave)) {
            return 1 - 2 * Math.pow((x - topRight) / d_minus_c, 2);
        } else if (Op.isLt(x, bottomRight)) {
            return 2 * Math.pow((x - bottomRight) / d_minus_c, 2);
        }

        return 0.0;
    }

    @Override
    public String toString() {
        String result = PiShape.class.getSimpleName();
        result += "(" + Op.join(", ", str(bottomLeft), str(topLeft),
                str(topRight), str(bottomRight)) + ")";
        return result;
    }

    public double getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(double bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public double getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(double topLeft) {
        this.topLeft = topLeft;
    }

    public double getTopRight() {
        return topRight;
    }

    public void setTopRight(double topRight) {
        this.topRight = topRight;
    }

    public double getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(double bottomRight) {
        this.bottomRight = bottomRight;
    }
}