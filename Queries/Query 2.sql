select Distinct Writer.Writername
from Writtenby natural join Writer
Where Writtenby.Adtypewritten = 'PictureType' and Writer.Payperhour<100
