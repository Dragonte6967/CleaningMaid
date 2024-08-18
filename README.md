# Cleaning Maid

一个用于Fabric的简单的扫地大妈MOD。

## 目前支持

- 当掉落物数量超过设定值时，执行清理；

- 在白名单里的不被清理。


## 配置方法

创建 `config/CleaningMaid/config.txt`，在第一行写数量阈值，然后一行一个白名单。

eg：

```
600
minecraft:egg
```

当掉落物超过600时，执行清理，并保留所有的鸡蛋。

或者直接运行后自动生成一个空的配置文件，自行修改并重启游戏


---
TODO：定期清理