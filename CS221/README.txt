// helloworld.asm
.text                               scripting portion of the code
  li $sv0, 4                        line to prep a type: string                 li = load immediate
  la $a0, output                    line to give the address of the string      la = load address
  syscall                           calls the print function
  
  li $v0, 1                         line to prep a type: integer
  li $a0, 7                         line to give the integer in this case
  syscall                           calls the print function
  
  li $v0, 4                         line to prep a type: string
  la $a0, prompt                    line to give the address of the string
  syscall
  
  li $v0, 8                        line to prep reading of type: string
  la $a0, buffer                   line to give the address to store string
  li $a1, 80                       limits the user to 80 bytes(characters)
  syscall
  
  li $v0, 4                        line to prep a type: string
  la $a0, newLine                  line to give the address of the string
  syscall
  
  li $v0, 4                        line to prep a type: string
  la $a0, buffer                   line to give the address of the string
  syscall
  
  li $v0, 10                        line to prep the command: exit
  syscall                           calls the exit command
  
.data                               data storage portion; where addresses are made
output: .asciiz "Hello World\n"     setting the address output to the ascii character string "Hello World\n"
prompt: .asciiz "Enter a string: "  setting the address output to the ascii character string
buffer: .space 80                   setting the character limit of buffer to 80
newLine: .asciiz "\n"               setting the address output to the ascii character string "\n"
