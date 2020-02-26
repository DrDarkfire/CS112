# pitch class list must be in a list form
def to_normal_form(pitchClassList):
    # rotate through list clockwise insert will shift everything to the right technically
    rotations = [pitchClassList]
    curr = pitchClassList
    for x in pitchClassList:
        # take the last postion and place it into first
        curr.insert(0, curr.pop)
        # add to rotations list
        
