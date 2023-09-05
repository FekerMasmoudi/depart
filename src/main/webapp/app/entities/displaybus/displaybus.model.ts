export interface IDisplaybus {
  id: string;
  lang?: string | null;
  vehicule?: string | null;
  num_appel?: number | null;
  detail_ligne?: string | null;
  ligne?: string | null;
  direction?: string | null;
  denumli?: string | null;
  deltyli?: string | null;
}

export type NewDisplaybus = Omit<IDisplaybus, 'id'> & { id: null };
