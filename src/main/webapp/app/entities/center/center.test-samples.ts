import { ICenter, NewCenter } from './center.model';

export const sampleWithRequiredData: ICenter = {
  id: 'a51e6a01-110a-4623-88e5-1d5cd90b0702',
  deccent: 92523,
};

export const sampleWithPartialData: ICenter = {
  id: 'dc7a3196-638a-4cb8-b70c-4cbe0bd32344',
  deccent: 67814,
  delcent: 'Total',
  deadrce: 'Bahamas Sierra',
};

export const sampleWithFullData: ICenter = {
  id: 'ca2bba26-a3fe-466a-bd0f-97387ee1d311',
  deccent: 84594,
  delcent: 'best-of-breed Ball Ghana',
  deadrce: 'implement Senior',
  deobser: 'navigating connect transmitting',
};

export const sampleWithNewData: NewCenter = {
  deccent: 91224,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
