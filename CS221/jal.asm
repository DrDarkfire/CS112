.text
    la $a0, prompt
    jal PromptInt
    move $s0, $v0
    
    li $v0, 4
    la $a0, output
    syscall
    
    li $v0, 1
    move $a0, $s0
    syscall
    
    j Exit // this will skip the lines that would be placed after it and go to the subprogram called Exit

.data
prompt: .asciiz "Enter a number"
output: .asciiz "You entered"

.text
Exit:
    li $v0, 10
    syscall
    
.text
# $a0 is the prompt
PromptInt:
    li $v0, 4
    syscall
    
    li $v0, 5
    syscall
    jr $ra
