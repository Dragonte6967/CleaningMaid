package top.dragonte.cleaningmaid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Cleaningmaid implements ModInitializer {
    private final Logger logger = Logger.getLogger("CleaningMaid");
    List<ItemEntity> list = new ArrayList<>();
    File file = new File("config/CleaningMaid/config.txt");
    List<String> contents = new ArrayList<>();

    @Override
    public void onInitialize() {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            if (sc.hasNextLine()) {
                while (sc.hasNextLine()) {
                    contents.add(sc.nextLine());
                }
            }
            sc.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!contents.isEmpty()) {
            ServerLifecycleEvents.SERVER_STARTED.register(server ->
                    ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
                        if (entity instanceof ItemEntity) {
                            list.add((ItemEntity) entity);
                        }
                        if (list != null && list.size() > Integer.parseInt(contents.get(0))) {
                            list.stream().filter(itemEntity -> !contents.contains(itemEntity.getStack().getItem().toString())).forEach(Entity::discard);
                            list.clear();
                            logger.info("CleaningMaid clear!");

                        }
                    })
            );
        } else {
            logger.warning("CleaningMaid config.txt is empty!");
        }

    }

}
