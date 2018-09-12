.text                               scripting portion of the code
  li $sv0, 4                        line to prep a type: string
  la $a0, output                    line to give the address of the string
  syscall                           calls the print function
  
  li $v0, 1                         line to prep a type: integer
  li $a0, 7                         line to give the integer in this case
  syscall                           calls the print function
  
  li $v0, 10                        line to prep the command: exit
  syscall                           calls the exit command
  
.data                               data storage portion; where addresses are made
output: .asciiz "Hello World\n"     setting the address output to the ascii character string "Hello World\n"
