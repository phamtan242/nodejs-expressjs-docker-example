1. Install protoc to compile .proto to java class
```sh
$ curl -LO https://github.com/protocolbuffers/protobuf/releases/download/v3.15.8/protoc-3.15.8-linux-x86_64.zip
$ unzip protoc-3.15.8-linux-x86_64.zip -d $HOME/.local
$ export PATH="$PATH:$HOME/.local/bin"
$ protoc --version
> libprotoc 3.15.8
```
2. Generate java code
```sh
$ protoc -I=. --java_out=<path_to_java_folder> <file.proto>
i.g:
$ protoc -I=. --java_out=../../java addressbook.proto
``` 