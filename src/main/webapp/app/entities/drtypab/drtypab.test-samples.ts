import { IDrtypab, NewDrtypab } from './drtypab.model';

export const sampleWithRequiredData: IDrtypab = {
  id: 'd23689a2-429d-4cf1-9716-02cd9559bca7',
};

export const sampleWithPartialData: IDrtypab = {
  id: 'ec137e48-dda7-4f21-a012-b9af5dbd31b3',
};

export const sampleWithFullData: IDrtypab = {
  id: '6f997a6a-8df2-491b-b87e-0d6c32d426f3',
  cdtypab: 'Tennessee',
  lbtypab: 'payment Markets',
  dabsjt: 'Berkshire Soft',
  dabsjp: 'mint',
};

export const sampleWithNewData: NewDrtypab = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
