package com.sample.cropview.helpers.dp

import com.sample.cropview.R
import com.sample.cropview.helpers.models.AspectRatio
import dev.pegasus.crop.aspectRatio.model.AspectRatioType

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/27/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

class DpAspectRatio {

    val list = listOf(
        AspectRatio(id = 0, text = "Free", iconResId = R.drawable.ic_aspect_ratio_free, aspectRatioType = AspectRatioType.ASPECT_FREE),
        AspectRatio(id = 1, text = "Ins 1:1", iconResId = R.drawable.ic_aspect_ratio_ins_1_1, aspectRatioType = AspectRatioType.ASPECT_INS_1_1),
        AspectRatio(id = 2, text = "Ins 4:5", iconResId = R.drawable.ic_aspect_ratio_ins_4_5, aspectRatioType = AspectRatioType.ASPECT_INS_4_5),
        AspectRatio(id = 3, text = "Story", iconResId = R.drawable.ic_aspect_ratio_ins_story, aspectRatioType = AspectRatioType.ASPECT_INS_STORY),
        AspectRatio(id = 4, text = "5:4", iconResId = R.drawable.ic_aspect_ratio_5_4, aspectRatioType = AspectRatioType.ASPECT_5_4),
        AspectRatio(id = 5, text = "3:4", iconResId = R.drawable.ic_aspect_ratio_3_4, aspectRatioType = AspectRatioType.ASPECT_3_4),
        AspectRatio(id = 6, text = "4:3", iconResId = R.drawable.ic_aspect_ratio_4_3, aspectRatioType = AspectRatioType.ASPECT_4_3),
        AspectRatio(id = 7, text = "Post", iconResId = R.drawable.ic_aspect_ratio_fb_post, aspectRatioType = AspectRatioType.ASPECT_FACE_POST),
        AspectRatio(id = 8, text = "Cover", iconResId = R.drawable.ic_aspect_ratio_fb_cover, aspectRatioType = AspectRatioType.ASPECT_FACE_COVER),
        AspectRatio(id = 9, text = "Post", iconResId = R.drawable.ic_aspect_ratio_pin_post, aspectRatioType = AspectRatioType.ASPECT_PIN_POST),
        AspectRatio(id = 10, text = "3:2", iconResId = R.drawable.ic_aspect_ratio_3_2, aspectRatioType = AspectRatioType.ASPECT_3_2),
        AspectRatio(id = 10, text = "9:16", iconResId = R.drawable.ic_aspect_ratio_9_16, aspectRatioType = AspectRatioType.ASPECT_9_16),
        AspectRatio(id = 10, text = "16:9", iconResId = R.drawable.ic_aspect_ratio_16_9, aspectRatioType = AspectRatioType.ASPECT_16_9),
        AspectRatio(id = 10, text = "1:2", iconResId = R.drawable.ic_aspect_ratio_1_2, aspectRatioType = AspectRatioType.ASPECT_1_2),
        AspectRatio(id = 10, text = "Cover", iconResId = R.drawable.ic_aspect_ratio_youtube_cover, aspectRatioType = AspectRatioType.ASPECT_YOU_COVER),
        AspectRatio(id = 10, text = "Post", iconResId = R.drawable.ic_aspect_ratio_twitter_post, aspectRatioType = AspectRatioType.ASPECT_TWIT_POST),
        AspectRatio(id = 10, text = "Header", iconResId = R.drawable.ic_aspect_ratio_twitter_header, aspectRatioType = AspectRatioType.ASPECT_TWIT_HEADER),
        AspectRatio(id = 10, text = "A4", iconResId = R.drawable.ic_aspect_ratio_a_4, aspectRatioType = AspectRatioType.ASPECT_A_4),
        AspectRatio(id = 10, text = "A5", iconResId = R.drawable.ic_aspect_ratio_a_5, aspectRatioType = AspectRatioType.ASPECT_A_5),
    )
}