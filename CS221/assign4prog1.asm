.text
# text to prompt user for an integer
li $v0, 4
la $a0, prompt
syscall

# obtain the integer
li $v0, 

.data
prompt: .asciiz "Enter an integer:"