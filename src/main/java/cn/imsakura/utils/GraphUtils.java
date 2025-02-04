package cn.imsakura.utils;

import cn.imsakura.PWCS;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
public class GraphUtils {

        public static File draw(Map<String, Object> map, String url) {

            // 将Map转换为String数组
            String[] text = map.entrySet().stream()
                    .map(entry -> entry.getKey() + ": " + entry.getValue())
                    .toArray(String[]::new);

            // 动态计算图片高度
            int width = 800; // 图片宽度固定
            int baseHeight = 400; // 基础高度
            int lineHeight = 50; // 每行文本的高度
            int dynamicHeight = baseHeight + (text.length - 4) * lineHeight; // 动态调整高度
            int height = Math.max(baseHeight, dynamicHeight);

            // 创建图片
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            // 开启高质量抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

            // 背景颜色（渐变色）
            GradientPaint gp = new GradientPaint(0, 0, new Color(30, 30, 30), width, height, new Color(10, 10, 10));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);

            // 头像大小 & 位置
            int avatarSize = 120;
            int avatarX = 40, avatarY = (height - avatarSize) / 2;

            try {
                // 读取头像
                BufferedImage avatar = ImageIO.read(new URL(url));

                // 裁剪为圆形
                BufferedImage circleAvatar = new BufferedImage(avatarSize, avatarSize, BufferedImage.TYPE_INT_ARGB);
                Graphics2D gAvatar = circleAvatar.createGraphics();
                gAvatar.setClip(new Ellipse2D.Float(0, 0, avatarSize, avatarSize));
                gAvatar.drawImage(avatar, 0, 0, avatarSize, avatarSize, null);
                gAvatar.dispose();

                // 绘制头像
                g2d.drawImage(circleAvatar, avatarX, avatarY, null);

                // 头像边框
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(4));
                g2d.drawOval(avatarX, avatarY, avatarSize, avatarSize);

            } catch (IOException e) {
                e.printStackTrace();
                PWCS.INSTANCE.getLogger().error("头像加载失败喵~(T_T)，请检查头像路径是否正确!");

            }

            // 设置中文字体

            Font font = new Font("Microsoft YaHei", Font.BOLD, 28);
            g2d.setFont(font);
            g2d.setColor(Color.WHITE);

            // 文字起始位置
            int textX = avatarX + avatarSize +100 ;
            int textY = Integer.max(avatarY-90,10);

            // 动态绘制文本内容
            for (String line : text) {
                g2d.drawString(line, textX, textY);
                textY += lineHeight; // 自动调整文本行间距
            }

            // 释放资源
            g2d.dispose();

            // 保存图片
            try {
                File outputFile = new File("game_profile_card.png");
                ImageIO.write(image, "png", outputFile);

                PWCS.INSTANCE.getLogger().info("已成功生成高清游戏信息卡片喵");
               // System.out.println("已成功生成高清游戏信息卡片喵！(ฅ>ω<*ฅ)");
                return outputFile;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }



