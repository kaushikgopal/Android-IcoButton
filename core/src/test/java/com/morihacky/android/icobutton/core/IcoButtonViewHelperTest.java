package com.morihacky.android.icobutton.core;

import org.junit.Before;
import org.junit.Test;

import static com.morihacky.android.icobutton.core.IcoButtonViewHelper.ICON_ALIGN_LEFT;
import static com.morihacky.android.icobutton.core.IcoButtonViewHelper.ICON_ALIGN_LEFT_OF_TEXT;
import static com.morihacky.android.icobutton.core.IcoButtonViewHelper.ICON_ALIGN_RIGHT;
import static com.morihacky.android.icobutton.core.IcoButtonViewHelper.ICON_ALIGN_RIGHT_OF_TEXT;
import static org.fest.assertions.api.Assertions.assertThat;

public class IcoButtonViewHelperTest {

    private IcoButtonViewHelper _ico;

    @Before
    public void setUp() throws Exception {
        _ico = new IcoButtonViewHelper();

        _ico.setTotalWidth(500);
        _ico.setTotalHeight(60);
        _ico.setHorizontalPadding(10);
        _ico.setSpacingBtwIconAndText(10); // TODO: change this to a value other than 10

        _ico.setIcoWidth(30);
        _ico.setTextHeight(30);
        _ico.setTextWidth(100);
    }

    @Test
    public void verticalCoordinatesForText_ShouldBeCorrect() {
        assertThat(_ico.getTopForText()).isEqualTo(15);
        assertThat(_ico.getBottomForText()).isEqualTo(15 + 30);
    }

    @Test
    public void verticalCoordinatesForIcon_ShouldBeTheSameAsThatOfText() {
        assertThat(_ico.getTopForIcon()).isEqualTo(_ico.getTopForText());
        assertThat(_ico.getBottomForIcon()).isEqualTo(_ico.getBottomForText());
    }

/*     |----------------------------------  500 -----------------------------|
        _____________________________________________________________________                              ---
       |                                                                     |                              |
       |               ________                                              |                  ---         |
       |              |        |       _____________________                 |           ---     |          |
       |              |        |      |                     |                |            |      |          |
       |              |        |      |                     |                |            30     40         |
       |              |  Ico   |      |        TEXT         |                |            |      |          60
       |              |        |      |_____________________|                |           ---     |          |
       |              |________|                                             |                  ---         |
       |                                                                     |                              |
       |_____________________________________________________________________|                              |
                                                                                                           ---
                      |-- 30 --| |-15-||------- 100 --------|
       |----  180 ----|                                      |----  180 ----|
*/

    @Test
    public void horizontalCoordinatesForIcon_ShouldBeCorrect_WhenIconAlignmentIsLeftOfText() {
        _ico.setIcoAlign(ICON_ALIGN_LEFT_OF_TEXT);
        assertThat(_ico.getLeftForIcon()).isEqualTo(180);
        assertThat(_ico.getRightForIcon()).isEqualTo(180 + 30);
    }

    @Test
    public void horizontalCoordinatesForText_ShouldBeCorrect_WhenIconAlignmentIsLeftOfText() {
        _ico.setIcoAlign(ICON_ALIGN_LEFT_OF_TEXT);
        assertThat(_ico.getLeftForText()).isEqualTo(180 + 40);
        assertThat(_ico.getRightForText()).isEqualTo(180 + 40 + 100);
    }

    /*
           |----------------------------------  500 ---------------------------|
            ___________________________________________________________________                              ---
           |                                                                   |                              |
           |                                            ________               |                  ---         |
           |                _____________________      |        |              |           ---     |          |
           |               |                     |     |        |              |            |      |          |
           |               |        TEXT         |     |  ICON  |              |            30     40         |
           |               |                     |     |        |              |            |      |          60
           |               |_____________________|     |        |              |           ---     |          |
           |                                           |________|              |                  ---         |
           |                                                                   |                              |
           |___________________________________________________________________|                              |
                                                                                                             ---
                           |------- 100 --------||-15-||-- 30 --|

           |----  180 ----|                                      |----  180 ----|
    */
    @Test
    public void horizontalCoordinatesForIcon_ShouldBeCorrect_WhenIconAlignmentIsIconAlignRightOfText() {
        _ico.setIcoAlign(ICON_ALIGN_RIGHT_OF_TEXT);
        assertThat(_ico.getLeftForIcon()).isEqualTo(290);
        assertThat(_ico.getRightForIcon()).isEqualTo(290 + 30);
    }

    @Test
    public void horizontalCoordinatesForText_ShouldBeCorrect_WhenIconAlignmentIsIconAlignRightOfText() {
        _ico.setIcoAlign(ICON_ALIGN_RIGHT_OF_TEXT);
        assertThat(_ico.getLeftForText()).isEqualTo(180);
        assertThat(_ico.getRightForText()).isEqualTo(180 + 100);
    }


/*
     |--------------------------- 500 -----------------|
      __________________________________________________                   ---
     |                                                 |                    |
     |    ________                                     |           ---      |
     |   |        |           ______________           |    ---     |       |
     |   |        |          |              |          |     |      |       |
     |   |  ICON  |          |     TEXT     |          |     30     40      |
     |   |        |          |              |          |     |      |       60
     |   |        |          |______________|          |    ---     |       |
     |   |________|                                    |           ---      |
     |                                                 |                    |
     |_________________________________________________|                    |
                                                                           ---
         |-- 30 --|          |---- 100 ----|
     |10|         |15|                              |10|
                     |--170--|             |--170--|
*/
    @Test
    public void horizontalCoordinatesForIcon_ShouldBeCorrect_WhenIconAlignmentIsIconAlignLeft() {
        _ico.setIcoAlign(ICON_ALIGN_LEFT);
        assertThat(_ico.getLeftForIcon()).isEqualTo(10);
        assertThat(_ico.getRightForIcon()).isEqualTo(40);
    }

    @Test
    public void horizontalCoordinatesForText_ShouldBeCorrect_WhenIconAlignmentIsIconAlignLeft() {
        _ico.setIcoAlign(ICON_ALIGN_LEFT);
        assertThat(_ico.getLeftForText()).isEqualTo(220);
        assertThat(_ico.getRightForText()).isEqualTo(320);
    }

    @Test
    public void horizontalCoordinatesForText_ShouldBeCorrect_WhenIconAlignmentIsIconAlignLeft_AndButtonWidthSmallToAccommodateOnlyContentWithPadding() {
        // 10 on each side for padding
        // 10 for space between
        // content widths 130

        _ico.setTotalWidth(160);
        _ico.setIcoAlign(ICON_ALIGN_LEFT);
        assertThat(_ico.getLeftForText()).isEqualTo(50);

        _ico.setTotalWidth(200);
        _ico.setIcoAlign(ICON_ALIGN_LEFT);
        assertThat(_ico.getLeftForText()).isEqualTo(70);
    }

/*     |--------------------  500 --------------------------|
        ____________________________________________________                    ---
       |                                                    |                    |
       |                                        ________    |           ---      |
       |            ______________             |        |   |    ---     |       |
       |           |              |            |        |   |     |      |       |
       |           |     TEXT     |            |  ICON  |   |     30     40      |
       |           |              |            |        |   |     |      |       60
       |           |______________|            |        |   |    ---     |       |
       |                                       |________|   |           ---      |
       |                                                    |                    |
       |____________________________________________________|                    |
                                                                                ---
                   |---- 100 ----|             |-- 30 --|
       |10|                                |15|         |10|

           |--170--|              |--170--|
*/

    @Test
    public void horizontalCoordinatesForIcon_ShouldBeCorrect_WhenIconAlignmentIsIconAlignRight() {
        _ico.setIcoAlign(ICON_ALIGN_RIGHT);
        assertThat(_ico.getLeftForIcon()).isEqualTo(460);
        assertThat(_ico.getRightForIcon()).isEqualTo(460 + 30);
    }

    @Test
    public void horizontalCoordinatesForText_ShouldBeCorrect_WhenIconAlignmentIsIconAlignRight() {
        _ico.setIcoAlign(ICON_ALIGN_RIGHT);
        assertThat(_ico.getLeftForText()).isEqualTo(180);
        assertThat(_ico.getRightForText()).isEqualTo(180 + 100);
    }

    @Test
    public void horizontalCoordinatesForText_ShouldBeCorrect_WhenIconAlignmentIsIconAlignRight_AndButtonWidthSmallToAccommodateOnlyContentWithPadding() {
        _ico.setTotalWidth(160);
        _ico.setIcoAlign(ICON_ALIGN_RIGHT);
        assertThat(_ico.getLeftForText()).isEqualTo(10);
    }
}
